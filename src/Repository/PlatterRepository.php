<?php

namespace App\Repository;

use App\Entity\Platter;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Platter|null find($id, $lockMode = null, $lockVersion = null)
 * @method Platter|null findOneBy(array $criteria, array $orderBy = null)
 * @method Platter[]    findAll()
 * @method Platter[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PlatterRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Platter::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Platter $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(Platter $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }
    public function find_Nb_Rec_Par_Status($type)
    {

        $em = $this->getEntityManager();

        $query = $em->createQuery(
            'SELECT DISTINCT  count(r.idplatter) FROM   App\Entity\Platter r  where r.typeplatter = :typeplatter   '
        );
        $query->setParameter('typeplatter', $type);
        return $query->getResult();
    }
    // /**
    //  * @return Platter[] Returns an array of Platter objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Platter
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
